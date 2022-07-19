package com.stewonello.fertilizer.dialect

import org.reflections.Reflections
import org.thymeleaf.dialect.AbstractProcessorDialect
import org.thymeleaf.processor.IProcessor
import org.thymeleaf.standard.StandardDialect

// TODO: Slot functionality: <fe:thing><div fe:slot="target" class="blue">lorem</div></fe:thing> or similar ...
// TODO: Functionality to handle simple body like JSX: <Comp>Text <span>or more</span></Comp> --> (props) => <div>{props.children}</div> ... via "standard slot"?
// TODO: Find components in sub folders? 'fragments/forms/input.html' <-- <fe:forms:input> ??

class FertilizerDialect(
    private val componentPackage: String, name: String = DIALECT_NAME, prefix: String = DIALECT_PREFIX, processorPrecedence: Int = StandardDialect.PROCESSOR_PRECEDENCE
) : AbstractProcessorDialect(
    name, prefix, processorPrecedence
) {
    companion object{
        private const val DIALECT_NAME = "Fertilizer Dialect"
        private const val DIALECT_PREFIX = "fe"
    }

    override fun getProcessors(dialectPrefix: String): HashSet<IProcessor> {

        val fertilizerComponentClasses = Reflections(componentPackage).getSubTypesOf(FertilizerComponent().javaClass)

        return fertilizerComponentClasses.map {
            // TODO: more fancy handling of name: camelcase to kebab case or snake case something like that, maybe even configurable
            val tagName = it.simpleName.lowercase() // ClassName -> classname
            FertilizerElementTagProcessor(DIALECT_PREFIX, tagName)
        }.toHashSet()
    }
}
