# Document Comparer

A Spring Boot application for comparing the similarity of a base document to a pool of other documents using word matching. Designed for efficient, extensible, and robust document comparison workflows.

## Features
- Compares a base file to all files in a specified directory
- Calculates similarity scores based on unique word overlap
- Outputs sorted results by similarity

## How It Works
1. Extracts unique words from a base file
2. Extracts unique words from each file in a target directory
3. Calculates a similarity score for each file (percentage of matching words over the union of words)
4. Prints results sorted by score

**Similarity Score Calculation:**

For each file, the similarity score is calculated as:

```
Score = (Number of matching words between base file and compared file) / (Total unique words in both files) × 100
```

Where:
- *Matching words* = words present in both the base file and the compared file
- *Total unique words* = the union of all unique words from both files

This gives a percentage representing how similar each file is to the base file based on word overlap.


## Getting Started

### Prerequisites
- Java 17
- Maven

### Build & Run
1. **Clone the repository:**
   ```sh
   git clone <your-repo-url>
   cd Document_Comparer
   ```
2. **Configure file paths:**
   Edit `src/main/resources/application.properties`:
   ```properties
   file.baseFilePath=path/to/base/file.txt
   file.inputFilesDir=path/to/pool/directory
   ```
3. **Build the project:**
   ```sh
   mvn clean package
   ```
4. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```

### Example Output
```
File: file1.txt           Score: 85.00%
File: file2.txt           Score: 72.50%
...
```