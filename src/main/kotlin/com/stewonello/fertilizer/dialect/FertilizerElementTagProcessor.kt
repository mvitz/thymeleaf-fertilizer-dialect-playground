package com.stewonello.fertilizer.dialect

import org.thymeleaf.context.ITemplateContext
import org.thymeleaf.engine.TemplateModel
import org.thymeleaf.model.AttributeValueQuotes
import org.thymeleaf.model.IModelFactory
import org.thymeleaf.model.IModel
import org.thymeleaf.model.ITemplateEvent
import org.thymeleaf.model.IProcessableElementTag
import org.thymeleaf.model.IElementTag
import org.thymeleaf.model.IStandaloneElementTag
import org.thymeleaf.model.IOpenElementTag
import org.thymeleaf.model.ICloseElementTag
import org.thymeleaf.processor.element.AbstractElementModelProcessor
import org.thymeleaf.processor.element.IElementModelStructureHandler
import org.thymeleaf.processor.element.IElementModelProcessor
import org.thymeleaf.standard.expression.Fragment
import org.thymeleaf.standard.expression.StandardExpressions
import org.thymeleaf.standard.expression.IStandardExpressionParser
import org.thymeleaf.standard.processor.StandardReplaceTagProcessor
import org.thymeleaf.templatemode.TemplateMode


class FertilizerElementModelProcessor(
    dialectPrefix: String, private val elementName: String, private val componentClass: Class<out FertilizerComponent>
) : AbstractElementModelProcessor(
    TemplateMode.HTML, dialectPrefix, elementName, true, null, false, PRECEDENCE
) {
    companion object {
        private const val PRECEDENCE: Int = StandardReplaceTagProcessor.PRECEDENCE
    }

    fun loadFragmentModel(context: ITemplateContext, expressionParser: IStandardExpressionParser): TemplateModel {
        val expression = "~{fragments/$elementName :: $elementName}"

        val fragmentExpression = expressionParser.parseExpression(context, expression)
        val fragment = fragmentExpression.execute(context) as Fragment
        return fragment.templateModel
    }

    fun extractSlots(tag: IModel): HashMap<String?, ArrayList<ITemplateEvent>> {
        var slots = HashMap<String?, ArrayList<ITemplateEvent>>()
        var slotName: String? = null
        var level = 0
        for (i in 1 until tag.size() - 1) {
            if (tag.get(i) is IOpenElementTag) {
                level++
            } 
            else if (tag.get(i) is ICloseElementTag) {
                level--
            }
            if ((level == 1) && (tag.get(i) is IProcessableElementTag) && (tag.get(i) as IProcessableElementTag).hasAttribute("fe:slot")) {
                slotName = (tag.get(i) as IProcessableElementTag).getAttributeValue("fe:slot")
            }
            slots.getOrPut(slotName) { ArrayList<ITemplateEvent>() }.add(tag.get(i))
            if ((level == 0) && (tag.get(i) is ICloseElementTag)) {
                slotName = null // Reset slotName when back on root level
            }
        }
        return slots
    }


    fun prepareModel(context: ITemplateContext, fragmentModel: TemplateModel, slots: HashMap<String?, ArrayList<ITemplateEvent>>): IModel {
        val modelFactory = context.modelFactory
        val newModel = modelFactory.createModel()

        // Copy all elements into a new Model
        for (i in 1 until fragmentModel.size()-1) {
            var fragmentPart = fragmentModel.get(i)
            if (fragmentPart is IStandaloneElementTag && fragmentPart.getElementCompleteName() == "fe:slot") {
                val slotName = fragmentPart.getAttributeValue("fe:name")
                if (slots.containsKey(slotName)) {
                    for (j in 0 until slots[slotName]!!.size) {
                        newModel.add(slots[slotName]!![j])
                    }
                }
            }
            else {
                newModel.add(fragmentPart)
            }
        }
        return newModel
    }

    fun extractAttrs(rootElement: IProcessableElementTag, context: ITemplateContext, expressionParser: IStandardExpressionParser): HashMap<String, Any?> {
        var attrs = HashMap<String, Any?>()
        rootElement.allAttributes.forEach {
            val plainAttributeName = it.attributeDefinition.attributeName.attributeName

            // Process and populate FertilizerDialect (fe:) prefixed attribute variables
            // using `th:` variant should also work mostly but has only Strings as return type
            if(it.attributeDefinition.attributeName.prefix == dialectPrefix) {
                val valueContent = expressionParser.parseExpression(context, it.value).execute(context)
                attrs.set(plainAttributeName, valueContent) 
            }
            else { 
                attrs.set(plainAttributeName, it.value)   
            }
        }
        return attrs
    }

    override fun doProcess(context: ITemplateContext, tag: IModel, structureHandler: IElementModelStructureHandler) {
        val expressionParser = StandardExpressions.getExpressionParser(context.configuration)
        val fragmentModel = loadFragmentModel(context, expressionParser)
        
        val slots = extractSlots(tag)
        val newModel = prepareModel(context, fragmentModel, slots)

        val attrs = extractAttrs(tag.get(0) as IProcessableElementTag, context, expressionParser)
        val componentContext = FertilizerComponentContext(attrs, slots.keys, context, structureHandler)
        var componentInstance = componentClass.constructors.first().newInstance(componentContext)
        structureHandler.setLocalVariable(elementName, componentInstance)
        
        tag.reset(); // clear the model reference
        tag.addModel(newModel); 
    }
}
