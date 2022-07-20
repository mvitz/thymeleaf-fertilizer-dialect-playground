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

- `fe:` prefixed attributes will be processed and populated as variable in fragment
- `th:` prefixed will be processed and put onto the root element of the fragment
- unprefixed attributes will just be put onto the root element of the fragment

```
<div>
    <fe:mycomponent data-foo="bar" th:data-lorem="${ipsum}" fe:text="'Some Lorem'" fe:foo="${bar}">
</div>
```

Ok ...

## Roadmap

See all the TODO:s in the project

- Mainly slots
- Compare performance to normal fragment usage
- Scoped variables 🥲
- 🤷‍♀️