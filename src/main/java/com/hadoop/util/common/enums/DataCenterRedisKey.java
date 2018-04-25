package com.hadoop.util.common.enums;

/**
 * Created by sai.luo on 2017-6-1.
 */
public enum DataCenterRedisKey {
    TOKEN_KEY("TOKEN_") ,
    UPLOAD_FILE_KEY("UPLOAD_FILE_"),
    IMPORT_FILE_TO_DATASOURCE_CONFIGUERATION_KEY("IMPORT_FILE_TO_DATASOURCE_CONFIGUERATION"),
    IMPORT_FILE_TO_DATASOURCE_METAINFO_KEY("IMPORT_FILE_TO_DATASOURCE_METAINFO"),
    UPDATE_FILE_TO_DATASOURCE_CONFIGUERATION_KEY("UPDATE_FILE_TO_DATASOURCE_CONFIGUERATION"),
    UPDATE_FILE_TO_DATASOURCE_REFERENCE_FIELD_KEY("UPDATE_FILE_TO_DATASOURCE_REFERENCE_FIELD"),
    TABLES_JOIN_SQL_KEY("TABLE_JOIN_SQL"),
    TABLE_DATA_INFO_QUERY_CONDITION_KEY("TABLE_DATA_INFO_QUERY_CONDITION"),
    REPORT_ANALYSE_DATA("REPORT_ANALYSE_DATA"),

    /**
     * 分报告数据
     */
    SUB_REPORT_GEN_DATA_KEY("SUB_REPORT_GEN_DATA_KEY"),
    /**
     * 生成分报告excel 头
     */
    WIDGET_EXCEL_HEADER_KEY("WIDGET_EXCEL_HEADER_KEY");

    private String key ;
    String REDIS_PREFIX = "DATA_CENTER_" ;

    DataCenterRedisKey(String key){
        this.key =  REDIS_PREFIX + key ;
    }

    public String getKey() {
        return key;
    }
}