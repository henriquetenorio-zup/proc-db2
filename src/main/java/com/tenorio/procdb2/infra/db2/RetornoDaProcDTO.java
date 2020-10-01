package com.tenorio.procdb2.infra.db2;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RetornoDaProcDTO {

    private Integer cdRetorno;
    private Integer cdSqlRet;
}
