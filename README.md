# Exporter
Export support in following format: (txt, csv, xls)
1. Text file  
2. CSV file 
3. Excel file 
 
#### Library Size : 1.137 Mb 
  
## Setup 
Add this to your project build.gradle
``` gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
``` 
#### Dependency
[![](https://jitpack.io/v/org.bitbucket.droidhelios/Exporter.svg)](https://jitpack.io/#org.bitbucket.droidhelios/Exporter)
```gradle
dependencies {
    implementation 'org.bitbucket.droidhelios:Exporter:x.y'
}
```   

In your activity class:
#### Prepare List for this library
```java 
    public static List<List<String>> getExporterList() {
        List<User> yourList = new ArrayList<>();
        yourList.add(new User("01","Alpha"));
        yourList.add(new User("02","Bita"));

        //add header in your exporting list
        yourList.add(0, new User("Id","Name"));

        //make exporter list
        List<List<String>> exporterList = new ArrayList<>();
        for (User item : yourList){
            exporterList.add(Arrays.asList(item.getId(),item.getName()));
        }
        return exporterList;
    }
```
#### Get Sample List
```java 
    List<List<String>> exporterList = ExportSample.getExporterList(); 
```
In your activity class:
#### Export in Text file
```java 
    Exporter.getInstance(this).TextBuilder()
            .setFileName("SampleText")
            .setFileBody("Hello World!")
            .setListener(new Callback<File>() {
                @Override
                public void onSuccess(File result) {

                }

                @Override
                public void onFailure(Exception e) {

                }
            }).export();
```
#### Export in CSV file
```java 
    Exporter.getInstance(this).CsvBuilder()
            .setFileName("SampleCsv")
            .setCsvData(exporterList)
            .setListener(new Callback<File>() {
                @Override
                public void onSuccess(File result) {
                    shareFile(MainActivity.this, result);
                }

                @Override
                public void onFailure(Exception e) {

                }
            }).export();
```
#### Export in Excel file
```java 
    Exporter.getInstance(this).ExcelBuilder()
            .setFileName("SampleExcel")
            .setExcelData(exporterList)
            .setListener(new Callback<File>() {
                @Override
                public void onSuccess(File result) {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }).export();
```

 

For showing list of files. 
```java 
    exportCsv.showListOfFiles(new Selector<File>() {
        @Override
        public void onSelect(File result) {
            shareFile(MainActivity.this, result);
        }
    });
```
