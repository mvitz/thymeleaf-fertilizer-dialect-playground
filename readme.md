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
```

Call components/fragments like so:

```
<div>
    <!-- yea its still thymeleaf -->
    <fe:mycomponent th:text="'Some Lorem'" th:foo="${bar}">
</div>
```

Ok ...

## Roadmap

See all the TODO:s in the project