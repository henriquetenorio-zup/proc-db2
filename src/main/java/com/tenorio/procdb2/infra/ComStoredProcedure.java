package com.tenorio.procdb2.infra;

import com.tenorio.procdb2.infra.db2.RetornoDaProcDTO;
import com.tenorio.procdb2.infra.db2.DesbloqueioPorIDStoredProcedureImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class ComStoredProcedure {

    private static final int ID_ORDEM_DESBLOQUEIO = 1;

    @Autowired
    @Qualifier("desbloqueioPorIDStoredProcedureImpl")
    private DesbloqueioPorIDStoredProcedureImpl myStoredProcedure;

    @PostConstruct
    public void executarProcDb2() {
        System.out.println("ComStoredProcedure");

        RetornoDaProcDTO retornoDaProcDTO = myStoredProcedure.executarProcDb2(ID_ORDEM_DESBLOQUEIO);

        System.out.println(retornoDaProcDTO.getCdSqlRet());
        System.out.println(retornoDaProcDTO.getCdRetorno());
    }

}
