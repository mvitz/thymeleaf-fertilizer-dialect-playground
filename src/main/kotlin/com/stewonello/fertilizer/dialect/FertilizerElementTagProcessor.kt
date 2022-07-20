package com.stewonello.fertilizer.dialect

import org.thymeleaf.context.ITemplateContext
import org.thymeleaf.engine.TemplateModel
import org.thymeleaf.model.IProcessableElementTag
import org.thymeleaf.processor.element.AbstractElementTagProcessor
import org.thymeleaf.processor.element.IElementTagStructureHandler
import org.thymeleaf.standard.expression.Fragment
import org.thymeleaf.standard.expression.StandardExpressions
import org.thymeleaf.templatemode.TemplateMode

// Inspired by
class FertilizerElementTagProcessor(
    dialectPrefix: String, private val elementName: String
) : AbstractElementTagProcessor(
    TemplateMode.HTML, dialectPrefix, elementName, true, null, false, PRECEDENCE
) {
    companion object {
        private const val PRECEDENCE: Int = 666 // TODO: find some slightly more meaningful standard precedence from thymeleaf!?
    }

    override fun doProcess(
        context: ITemplateContext,
        tag: IProcessableElementTag,
        structureHandler: IElementTagStructureHandler
    ) {
        val expressionParser = StandardExpressions.getExpressionParser(context.configuration)
        // TODO: Make location configurable. (Even independent from thymeleaf location?)
        // TODO: Would it be easier to mimic the props directly into the fragment parameters?
        // TODO: Can we actually not rely on mimicking a template expression? 😇 Would that be better at all?
        val testFragmentExpression = "~{fragments/$elementName :: $elementName}"

        val fragmentExpression = expressionParser.parseExpression(context, testFragmentExpression)
        val fragment: Fragment = fragmentExpression.execute(context) as Fragment

        val fragmentModel: TemplateModel = fragment.templateModel
        structureHandler.setTemplateData(fragmentModel.templateData)

        tag.allAttributes.forEach {
            val plainAttributeName = it.attributeDefinition.attributeName.attributeName

            if (!it.attributeDefinition.attributeName.isPrefixed) {
                structureHandler.setLocalVariable(plainAttributeName, it.value)
            } else {
                // It seems not efficient to extract and reassign the values.
                // Why doesn't it work like here??:
                // https://github.com/thymeleaf/thymeleaf/blob/120a0e9cc5d768a7b21abb19b4f4122bdc019206/lib/thymeleaf/src/main/java/org/thymeleaf/standard/processor/AbstractStandardFragmentInsertionTagProcessor.java#L264-L271
                val valueContent = expressionParser.parseExpression(context, it.value).execute(context)
                structureHandler.setLocalVariable(plainAttributeName, valueContent)
            }
        }

        structureHandler.replaceWith(fragmentModel, true)
    }
}
