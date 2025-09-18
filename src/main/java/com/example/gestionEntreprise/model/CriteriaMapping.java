package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class CriteriaMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCriteriaMapping;

    @Column(nullable = false, unique = true, length = 50)
    private String criteriaType;

    @Column(nullable = false, length = 50)
    private String tableName;

    @Column(nullable = false, length = 50)
    private String columnName;

    @Column(nullable = false, length = 20)
    private String dataType;

    @Column(length = 10)
    private String defaultOperator = "=";

    // Getters and setters
    public Long getIdCriteriaMapping() { return idCriteriaMapping; }
    public void setIdCriteriaMapping(Long idCriteriaMapping) { this.idCriteriaMapping = idCriteriaMapping; }
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
