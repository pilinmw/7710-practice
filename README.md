# 7710 Practice

This repository contains COMP7710 practice materials organized into:

- `lab-exercise/`: Java lab exercise files.
- `mock/`: mock exam questions, source files, and provided tests.
- `finalexam/`: final exam review notes, lecture materials, transcripts, and requirement document.

## Compile Java Sources

```bash
javac $(find lab-exercise -name "*.java" ! -name "ProvidedTests.java")
javac mock/src/*.java
```
