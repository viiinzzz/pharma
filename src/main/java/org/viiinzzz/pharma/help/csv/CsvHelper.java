package org.viiinzzz.pharma.help.csv;

import java.io.Reader;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;


public class CsvHelper {

    public static <T extends CsvBean> List<T> ReadCsv(Reader reader, Class<T> clazz)
    throws Exception {
   
        CsvTransfer<T> csvTransfer = new CsvTransfer<T>();

        CsvToBean<CsvBean> cb = new CsvToBeanBuilder<CsvBean>(reader)
            .withSeparator(';')
            .withType(clazz)
            .build();

        csvTransfer.setCsvList((List<T>) cb.parse());

        return csvTransfer.getCsvList();
    }
}
