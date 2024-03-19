package com.appsfeature.exporter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import com.exporter.Exporter;
import com.exporter.csv.ExportCsv;
import com.exporter.excel.ExportExcel;
import com.exporter.listener.ExporterCallback;
import com.exporter.model.ExporterData;
import com.exporter.sample.ExportSample;
import com.exporter.text.ExportText;
import com.exporter.util.ExIntentShare;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ExportText exportText;
    private ExportExcel exportExcel;
    private ExportCsv exportCsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<List<String>> excelBody = ExportSample.getExporterList();
        exportDataInExcelFile(excelBody);
    }

    private void exportDataInTextFile() {
        Exporter.getInstance().TextBuilder(this)
                .setFileName("SampleText")
                .setData(new ExporterData("Hello World!"))
                .setListener(new ExporterCallback<File>() {
                    @Override
                    public void onSuccess(File result) {

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                }).export();
    }

    private void exportDataInExcelFile(List<List<String>> body) {
        Exporter.getInstance().ExcelBuilder(this)
                .setFileName("SampleExcel")
                .setData(new ExporterData(body))
                .setListener(new ExporterCallback<File>() {
                    @Override
                    public void onSuccess(File result) {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }).export();
    }

    private void exportDataInCsvFile(final Context context, List<List<String>> body) {
        Exporter.getInstance().CsvBuilder(this)
                .setFileName("SampleCsv")
                .setData(new ExporterData(body))
                .setListener(new ExporterCallback<File>() {
                    @Override
                    public void onSuccess(File result) {
                        shareFile(MainActivity.this, result);
                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                }).export();
//
//        exportCsv.showListOfFiles(new Selector<File>() {
//            @Override
//            public void onSelect(File result) {
//                shareFile(MainActivity.this, result);
//            }
//        });
    }

    private void shareFile(Context context, File result) {
        Uri uri = Utility.getUriFromFile(context, result);
        ExIntentShare.sharePdfFileThroughEmail(context, uri, "", "Sample " + result.getName(), "PFA");
    }

}
