package com.stewonello.fertilizer.dialect

import org.reflections.Reflections
import org.thymeleaf.dialect.AbstractProcessorDialect
import org.thymeleaf.processor.IProcessor
import org.thymeleaf.standard.StandardDialect

/* TODO: Slots and tag body
    Functionality to handle simple body 'like JSX'(?) ...via a predefined slot?:
    https://vuejs.org/guide/components/slots.html
    .
    .
    <fe:comp>
        Text <span>or more</span>
    </fe:comp>
    <fe:comp>
        <!-- Replaces target slot -->
        <div fe:slot="heading">Yada yada</fe:slot>
        <!-- Use default here <slot></slot> -->
        <div fe:slot="default">Yolo yolo</fe:slot>
    </fe:comp>
    .
    .
    <div th:fragment="comp">
        <fe:slot name="heading"></slot>
        <!-- NO NAME: Use normal tag body -->
        <fe:slot></slot>
    </div> */

// TODO: Find components in sub folders? 'fragments/forms/input.html' <-- <fe:forms:input> ??

// TODO: Make componentPackage name somehow available for Processors? For some features down the line? Why?

// TODO: A fe:props attribute: <fe:mycomponent fe:props="${thatVar}"> can just extract thatVar into all the fragment parameters?

/* TODO: Tie a 'MyComponentProps' tight to 'MyComponent'?
    'MyComponentProps' optional or required (raise Exception when missing??)? */


// TODO: aha ... hm scoped variables by handling variables in a "fertilizer namespace" map and clearing that one on any new processing?

// TODO: <fe:component title="add to component root element" th:title="'populate as variable'">

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
