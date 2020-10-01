package com.tenorio.procdb2.infra.db2;

public enum ParametrosProcDb2Enum {
    IN__ID_DESBLOQUEIO ("id"),

    OUT__CD_RETORNO("cdRetorno"),

    OUT__CD_SQL_RET("cdSqlRet");

    private String nomeDoParametro;

    ParametrosProcDb2Enum(String nomeDoParametro) {
        this.nomeDoParametro = nomeDoParametro;
    }

    @Override
    public String toString() {
        return nomeDoParametro;
    }
}
