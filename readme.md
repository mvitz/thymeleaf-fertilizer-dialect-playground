# Fertilizer

Here: Spring Boot, Kotlin

## How To

Supply with package name of your component classes:

```
@Configuration
class Config {

    @Bean
    fun fertilizerDialect(): FertilizerDialect {
        return FertilizerDialect("com.stewonello.fertilizer.components")
    }
}
```

The classes created in that package will map to thymeleaf fragment definitions.  
We will just use normal thymeleaf fragments to create our components.

```
package com.stewonello.fertilizer.components

import com.stewonello.fertilizer.dialect.FertilizerComponent

// Will map to "<templates>/fragments/mycomponent.html"
class MyComponent : FertilizerComponent()

// Tip: You can add classes here to provide type hints
data class MyComponentProps(
    val title: String,
    val content: String
)
```

Call components/fragments like so:

```
<div>
    <!-- yea its still thymeleaf -->
    <fe:mycomponent text="Some Lorem" th:foo="${bar}">
</div>
```

Ok ...

## Roadmap

See all the TODO:s in the project