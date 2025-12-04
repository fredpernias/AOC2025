# maven-java21

Minimal Maven project configured for Java 21.

Quick start

```powershell
cd "C:\Users\fpernias\OneDrive - Capgemini\Documents\AoC2025\maven-java21"
# Verify Maven installation
mvn -v
# Run tests
mvn test
# Build package
mvn package
```

Notes

- Uses `maven.compiler.release` = 21 and `maven-compiler-plugin` `<release>21`.
- JUnit 5 is configured for tests.
