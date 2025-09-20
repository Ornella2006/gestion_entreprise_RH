package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
@Table(name="criteria_mapping")
public class CriteriaMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCriteria_Mapping")
    private Integer idCriteriaMapping;

    @Column(nullable = false, unique = true, length = 50, name = "criteria_type")
    private String criteriaType;

    @Column(nullable = false, length = 50,name = "table_name")
    private String tableName;

    @Column(nullable = false, length = 50,name = "column_name")
    private String columnName;

    @Column(nullable = false, length = 20,name = "data_type")
    private String dataType;

    @Column(length = 10,name = "default_operator")
    private String defaultOperator = "=";

    // Getters and setters
    public Integer getIdCriteriaMapping() { return idCriteriaMapping; }
    public void setIdCriteriaMapping(Integer idCriteriaMapping) { this.idCriteriaMapping = idCriteriaMapping; }
    public String getCriteriaType() { return criteriaType; }
    public void setCriteriaType(String criteriaType) { this.criteriaType = criteriaType; }
    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }
    public String getColumnName() { return columnName; }
    public void setColumnName(String columnName) { this.columnName = columnName; }
    public String getDataType() { return dataType; }
    public void setDataType(String dataType) { this.dataType = dataType; }
    public String getDefaultOperator() { return defaultOperator; }
    public void setDefaultOperator(String defaultOperator) { this.defaultOperator = defaultOperator; }
}
