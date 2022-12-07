package com.stewonello.fertilizer.dialect

import org.thymeleaf.context.ITemplateContext
import org.thymeleaf.engine.TemplateModel
import org.thymeleaf.model.AttributeValueQuotes
import org.thymeleaf.model.IModelFactory
import org.thymeleaf.model.IModel
import org.thymeleaf.model.ITemplateEvent
import org.thymeleaf.model.IProcessableElementTag
import org.thymeleaf.processor.element.AbstractElementTagProcessor
import org.thymeleaf.processor.element.IElementTagStructureHandler
import org.thymeleaf.standard.expression.Fragment
import org.thymeleaf.standard.expression.StandardExpressions
import org.thymeleaf.standard.expression.IStandardExpressionParser
import org.thymeleaf.standard.processor.StandardReplaceTagProcessor
import org.thymeleaf.templatemode.TemplateMode


class FertilizerElementTagProcessor(
    dialectPrefix: String, private val elementName: String, private val componentClass: Class<out FertilizerComponent>
) : AbstractElementTagProcessor(
    TemplateMode.HTML, dialectPrefix, elementName, true, null, false, PRECEDENCE
) {
    companion object {
        private const val PRECEDENCE: Int = StandardReplaceTagProcessor.PRECEDENCE
    }

    fun loadFragmentModel(context: ITemplateContext, expressionParser: IStandardExpressionParser): TemplateModel {
        // TODO: Make location configurable. (Even independent from thymeleaf location?)
        // TODO: Would it be easier to mimic the props directly into the fragment parameters?
        // TODO: Can we actually not rely on mimicking a template expression? 😇 Would that be better at all?
        val expression = "~{fragments/$elementName :: $elementName}"

        val fragmentExpression = expressionParser.parseExpression(context, expression)
        val fragment = fragmentExpression.execute(context) as Fragment
        // TODO: maybe some handling for unwanted Fragment values
        return fragment.templateModel
    }

    fun extractSlots(tag: IProcessableElementTag): HashMap<String, ITemplateEvent> {
       var slots = HashMap<String, ITemplateEvent>()
       return slots
    }

    fun prepareModel(context: ITemplateContext, fragmentModel: TemplateModel, slots: HashMap<String, ITemplateEvent>): IModel {
        val modelFactory = context.modelFactory
        val newModel = modelFactory.createModel()

        // Copy all elements into a new Model
        for (i in 1 until fragmentModel.size()-1) {
            var fragmentPart = fragmentModel.get(i)
            println("" + fragmentPart.javaClass)
            if (fragmentPart is org.thymeleaf.model.IStandaloneElementTag) {
                val fp = fragmentPart as org.thymeleaf.model.IElementTag
                if (fp.getElementCompleteName() == "fe:slot") {
                  fragmentPart = slots["test"]
                }
            }
            newModel.add(fragmentPart)
        }
        return newModel
    }

    override fun doProcess(context: ITemplateContext, tag: IProcessableElementTag, structureHandler: IElementTagStructureHandler) {
        val expressionParser = StandardExpressions.getExpressionParser(context.configuration)
        val fragmentModel = loadFragmentModel(context, expressionParser)
        val slots = extractSlots(tag)
        val newModel = prepareModel(context, fragmentModel, slots)

        var attrs = HashMap<String, Any?>()
        tag.allAttributes.forEach {
            val plainAttributeName = it.attributeDefinition.attributeName.attributeName

            // Process and populate FertilizerDialect (fe:) prefixed attribute variables
            if(it.attributeDefinition.attributeName.prefix == dialectPrefix) {
                // It seems not efficient to extract and reassign the values.
                // Why doesn't it work like here??:
                // https://github.com/thymeleaf/thymeleaf/blob/120a0e9cc5d768a7b21abb19b4f4122bdc019206/lib/thymeleaf/src/main/java/org/thymeleaf/standard/processor/AbstractStandardFragmentInsertionTagProcessor.java#L264-L271
                val valueContent = expressionParser.parseExpression(context, it.value).execute(context)
               // structureHandler.setLocalVariable(plainAttributeName, valueContent)
               attrs.set(plainAttributeName, valueContent) 
            }
            else { 
                attrs.set(plainAttributeName, it.value)   
            }
        }

        var componentInstance = componentClass.constructors.first().newInstance(attrs)

        structureHandler.setLocalVariable("it", componentInstance)
        structureHandler.replaceWith(newModel, true)
    }
}
