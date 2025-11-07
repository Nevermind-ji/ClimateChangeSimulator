# Climate Change Simulator (Java Swing, OOP)

A small Java OOP project that simulates how user-controlled factors (Population, Forest Cover, Industries, Sustainability, and Disasters) affect climate values (Rainfall, Temperature, Water Availability, Pollution, Sea Level, Projection Score) and visualizes a simple tile map.

## How to build/run (no external libraries)

```bash
cd ClimateChangeSimulator
javac -d out $(find src -name "*.java")
java -cp out com.ccs.Main
```

On Windows (PowerShell):

```powershell
cd ClimateChangeSimulator
Get-ChildItem -Recurse -Filter *.java | % { $_.FullName } | javac -d out -@
java -cp out com.ccs.Main
```

## What it demonstrates (mapping to syllabus)

- **Classes/Objects & OOP basics:** `World`, `Tile`, `Environment`, `UserInput`
- **Inheritance & Abstraction:** `Disaster` → `Flood`, `Drought`, `Wildfire`
- **Aggregation/Composition:** `World` has a grid of `Tile`s
- **Packages:** `com.ccs.model`, `com.ccs.logic`, `com.ccs.ui`
- **Collections:** 2D array of tiles; could be swapped for `ArrayList`
- **Exception Handling:** (basic project is safe; add validation for extensions)
- **Multithreading:** Swing `Timer` to auto-run ticks (UI-friendly)
- **Strings/IO:** Environment report text; you can add save/load easily

## Notes / Ideas to extend
- Save & load scenarios to JSON or text
- Add more tile types and region-level stats
- Animate disasters (temporary color overlays)
- Charts for time series (basic Swing drawing)
