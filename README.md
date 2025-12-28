# KMP Platform Foundation [![](https://jitpack.io/v/cagdasc/kmp-platform-foundation.svg)](https://jitpack.io/#cagdasc/kmp-platform-foundation)

![Kotlin multiplatform](https://img.shields.io/badge/Kotlin-Multiplatform-blueviolet?style=for-the-badge)
![Android](https://img.shields.io/badge/Android-supported-brightgreen?style=for-the-badge)
![JVM](https://img.shields.io/badge/JVM-supported-brightgreen?style=for-the-badge)

A Kotlin Multiplatform (KMP) **foundation library** that provides reusable building blocks for **platform abstractions
**, **coroutines**, and **dependency injection**.

This repository intentionally contains **no UI modules**. UI-related concerns (themes, design system, components) are
expected to live in a **separate project**.

The goal of this project is to act as a **stable base layer** shared across multiple applications and higher-level
libraries.

---

## Modules Overview

### `core`

Platform abstractions and shared utilities that require platform-specific implementations.

**Responsibilities**:

* Expose platform concepts such as Activity, application info, permissions, intents, and configuration
* Provide clean, testable APIs that hide platform details from consumers
* Centralize platform-specific code outside of feature modules

---

### `core-di`

Dependency Injection bindings for platform-level abstractions.

**Responsibilities**:

* Provide DI modules for `core`
* Keep DI setup isolated from business logic and feature modules
* Avoid leaking DI framework details to consumers

This module depends on `core` and exposes only DI-related wiring.

---

### `coroutines`

Shared coroutine utilities and platform-aware dispatcher definitions.

**Responsibilities**:

* Define consistent coroutine dispatchers per platform
* Provide commonly used coroutine extensions
* Remove dispatcher configuration duplication across projects

---

### `coroutines-di`

Dependency Injection bindings for coroutine-related components.

**Responsibilities**:

* Provide DI modules for coroutine dispatchers and related utilities
* Centralize coroutine wiring for consistency across applications

This module depends on `coroutines`.

---

## Module Usage

Each module is published independently and can be consumed selectively.

```kotlin
dependencies {
    implementation("com.github.cagdasc.kmp-platform-foundation:core:<version>")
    implementation("com.github.cagdasc.kmp-platform-foundation:coroutines:<version>")

    // Optional
    implementation("com.github.cagdasc.kmp-platform-foundation:core-di:<version>")
    implementation("com.github.cagdasc.kmp-platform-foundation:coroutines-di:<version>")
}
```

## License

    Copyright 2025 Cagdas Caglak
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
