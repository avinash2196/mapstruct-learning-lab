> This repository is intended for learning, experimentation, and reference purposes. It is not designed as a production-grade system.

# mapstruct-learning-lab

**A hands-on reference project demonstrating MapStruct object-mapping patterns in Spring Boot.**

---

## Overview

This project shows how [MapStruct](https://mapstruct.org/) eliminates boilerplate mapping code in Java applications. It covers everything from simple field-name mismatches to complex multi-source merging with post-processing hooks.

Each mapper in this project is a deliberate, focused example of a single MapStruct concept. The REST endpoint wires them together so you can observe the output in a running application.

**Why this matters in real systems:**  
Applications frequently work with multiple representations of the same data — API DTOs, domain objects, persistence entities, event payloads. Writing this conversion code by hand is tedious, error-prone, and hard to maintain. MapStruct generates it at compile time with full type safety, giving you performance equivalent to hand-written code.

---

## Real-World Context

MapStruct is widely used in layered Spring Boot architectures to convert between:

| Layer boundary | Typical mapping |
|---|---|
| REST API ↔ Service | `*Request` / `*Response` ↔ domain object |
| Service ↔ Repository | Domain object ↔ JPA entity |
| Microservice events | Internal model ↔ Kafka/SQS event schema |
| Legacy integration | Third-party API response ↔ internal DTO |

---

## What This Repo Demonstrates

| Mapper | MapStruct concept |
|---|---|
| `CarMapper` | Explicit field-name mapping (`seatCount` → `numberOfSeats`) |
| `ObjWithArrayMapper` | Collection mapping + mapper composition (`uses`) |
| `SourceTargetMapper` | `@Qualifier` annotation for disambiguating list-to-scalar extraction |
| `NoIterableToIterableMapper` | `@Named` method — wrapping a scalar in a list |
| `ListToStringMapper` | Inline Java `expression` for complex extraction logic |
| `TestMapper` | `@MappingTarget` — updating an existing object; combining two sources |
| `ObjMapper` | `@AfterMapping` post-processing to merge two correlated lists by key |

---

## Architecture / Component Flow

```
HTTP Client
    │
    ▼
MappingDemoController  (GET /api/nested-mapping)
    │
    ▼
ObjMapper.convert(SourceObject)
    │
    ├── [MapStruct generated code]
    │   └── SourceObjectLevel2Obj1Mapper.convert()  per list element
    │         maps Obj1 fields → partial TargetObject
    │
    └── @AfterMapping: mergeObj2Fields()
          correlates Obj2List by matching key
          writes val2 into each TargetObject
    │
    ▼
TargetObjectWrapper  (JSON response)
```

**Request lifecycle (nested mapping endpoint):**

1. `MappingDemoController` assembles a `SourceObject` with two nested lists.
2. `ObjMapper.convert()` is called — MapStruct iterates `Obj1List`, delegating each element to `SourceObjectLevel2Obj1Mapper`.
3. After the main mapping completes, the `@AfterMapping` method runs and stitches `val2` from `Obj2List` into each target, matched by shared key.
4. The fully merged `TargetObjectWrapper` is serialised to JSON.

---

## Tech Stack

| Technology | Version | Role |
|---|---|---|
| Java | 17 | Language |
| Spring Boot | 2.7.4 | Web framework, DI container |
| MapStruct | 1.5.3.Final | Compile-time mapping code generation |
| Lombok | 1.18.24 | Boilerplate elimination (getters, builders) |
| JUnit 5 | (via Spring Boot) | Unit and integration testing |
| Maven | 3.x | Build tool |

---

## Project Structure

```
mapstruct-learning-lab/
├── src/
│   ├── main/
│   │   ├── java/org/example/
│   │   │   ├── SpringBootApp.java                      # Application entry point
│   │   │   ├── controller/
│   │   │   │   └── MappingDemoController.java          # REST endpoint
│   │   │   ├── mapper/
│   │   │   │   ├── CarMapper.java                      # Basic field-name mapping
│   │   │   │   ├── ObjWithArrayMapper.java             # Collection mapping + composition
│   │   │   │   ├── SourceTargetMapper.java             # @Qualifier list-to-scalar
│   │   │   │   ├── NoIterableToIterableMapper.java     # @Named scalar-to-list
│   │   │   │   ├── ListToStringMapper.java             # expression-based extraction
│   │   │   │   ├── TestMapper.java                     # @MappingTarget two-source merge
│   │   │   │   ├── ObjMapper.java                      # @AfterMapping correlated lists
│   │   │   │   └── SourceObjectLevel2Obj1Mapper.java   # Leaf mapper (used by ObjMapper)
│   │   │   ├── model/                                  # Domain / DTO classes
│   │   │   └── util/
│   │   │       ├── FirstElement.java                   # @Qualifier annotation
│   │   │       ├── LastElement.java                    # @Qualifier annotation
│   │   │       └── IterableNonIterableUtil.java        # Qualifier method provider
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/org/example/
│           ├── controller/
│           │   └── MappingDemoControllerTest.java      # Integration test (full context)
│           └── mapper/
│               ├── CarMapperTest.java
│               ├── ObjMapperTest.java
│               ├── ObjWithArrayMapperTest.java
│               ├── ListToStringMapperTest.java
│               ├── NoIterableToIterableMapperTest.java
│               ├── SourceTargetMapperTest.java
│               └── TestMapperTest.java
├── .gitignore
├── LICENSE
├── pom.xml
└── README.md
```

---

## How to Run Locally

**Prerequisites:** Java 17+, Maven 3.x

```bash
# Clone the repository
git clone https://github.com/<your-username>/mapstruct-learning-lab.git
cd mapstruct-learning-lab

# Build and run
mvn spring-boot:run
```

The server starts on **http://localhost:8086**.

Try the nested mapping endpoint:
```bash
curl http://localhost:8086/api/nested-mapping
```

<details>
<summary>Build the JAR and run manually</summary>

```bash
mvn clean package
java -jar target/mapstruct-learning-lab-1.0.0.jar
```

</details>

---

## How to Run Tests

```bash
# Run all tests
mvn test

# Run a single test class
mvn test -Dtest=CarMapperTest

# Run with verbose output
mvn test -Dsurefire.printSummary=true
```

MapStruct generates mapper implementation classes during the `compile` phase. Tests reference these generated classes, so a `mvn compile` is required before running tests in isolation.

---

## Example Usage

**GET /api/nested-mapping**

Response:
```json
{
  "targetObjectList": [
    {
      "key": "key1",
      "obj1": "obj11",
      "val1": "val11",
      "val2": "val11_2",
      "obj2": null
    },
    {
      "key": "key2",
      "obj1": "obj21",
      "val1": "val21",
      "val2": "val21_2",
      "obj2": null
    }
  ]
}
```

Notice that `val2` is populated from a *separate* source list, joined by the shared `key` field — that is the `@AfterMapping` hook at work.

---

## Learning Outcomes

After studying this project you will understand:

- How MapStruct generates mapping code at compile time and why this is safer than runtime reflection
- When to use implicit mapping (same field names) vs. explicit `@Mapping` (different names)
- How to compose small, single-purpose mappers into larger pipelines via `uses`
- When to use `@Qualifier` / `@Named` to disambiguate multiple conversion paths
- How to handle list-to-scalar and scalar-to-list conversions idiomatically
- How `@AfterMapping` and `@MappingTarget` enable complex post-processing without losing MapStruct's code generation benefits
- How to write focused mapper unit tests using a minimal Spring context

---

## Limitations

- **Not production-ready.** This is a learning reference with hard-coded sample data and no persistence, security, or error handling.
- The `@AfterMapping` join in `ObjMapper` is O(n×m). A real system with large data sets would use a `Map<String, Obj2>` for O(n) lookup.
- The `ListToStringMapper` uses an inline `expression = "java(...)"` which bypasses MapStruct's null-safety generation — acceptable for illustration, not recommended for production.
- `Source1` / `Target1` models are retained as reference for a type-conversion scenario; the corresponding mapper method is commented out.

---

## Future Improvements

- Add a service layer to separate data assembly from the controller
- Demonstrate `@InheritConfiguration` and `@InheritInverseConfiguration` for bidirectional mappers
- Add a persistence layer example (JPA entity ↔ domain object) showing a common real-world mapping boundary
- Demonstrate `@BeanMapping(nullValuePropertyMappingStrategy = IGNORE)` for partial update (PATCH) patterns

---

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.

