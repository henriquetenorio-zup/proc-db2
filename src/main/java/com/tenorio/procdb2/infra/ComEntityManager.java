package com.tenorio.procdb2.infra;

import com.tenorio.procdb2.infra.db2.ParametrosProcDb2Enum;
import com.tenorio.procdb2.infra.db2.RetornoDaProcDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

@Repository
public class ComEntityManager {

    @Autowired
    private EntityManager em;

    @Value("${procedure.desbloqueio}")
    private String nomeProcedure;

    private static final int ID_ORDEM_DESBLOQUEIO = 1 ;

    @PostConstruct
    public void executarProcDb2() {
        System.out.println("ComEntityManager");

        //Nome da impl
        StoredProcedureQuery query = em.createStoredProcedureQuery(nomeProcedure);

        //Tipos dos parametros
        query.registerStoredProcedureParameter(ParametrosProcDb2Enum.IN__ID_DESBLOQUEIO.toString(), Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(ParametrosProcDb2Enum.OUT__CD_SQL_RET.toString(), Integer.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter(ParametrosProcDb2Enum.OUT__CD_RETORNO.toString(), Integer.class, ParameterMode.OUT);

        //Passando parametros
        query.setParameter(ParametrosProcDb2Enum.IN__ID_DESBLOQUEIO.toString(), ID_ORDEM_DESBLOQUEIO);

        query.execute();

        RetornoDaProcDTO retornoDaProcDTO = RetornoDaProcDTO.builder()
                .cdRetorno((Integer) query.getOutputParameterValue(ParametrosProcDb2Enum.OUT__CD_RETORNO.toString()))
                .cdSqlRet((Integer) query.getOutputParameterValue(ParametrosProcDb2Enum.OUT__CD_SQL_RET.toString()))
                .build();

        System.out.println(retornoDaProcDTO.getCdSqlRet());
        System.out.println(retornoDaProcDTO.getCdRetorno());
    }

}
