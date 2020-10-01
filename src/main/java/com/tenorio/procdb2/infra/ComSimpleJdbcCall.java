package com.tenorio.procdb2.infra;

import com.tenorio.procdb2.infra.db2.ParametrosProcDb2Enum;
import com.tenorio.procdb2.infra.db2.RetornoDaProcDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Types;
import java.util.Map;

@Repository
public class ComSimpleJdbcCall {

    private SimpleJdbcCall simpleJdbcCall;

    private static final int ID_ORDEM_DESBLOQUEIO = 1;

    @Autowired
    public ComSimpleJdbcCall(JdbcTemplate jdbcTemplate, @Value("${procedure.desbloqueio}") String nomeProcDb2) {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName(nomeProcDb2)
                .declareParameters(
                        new SqlParameter(ParametrosProcDb2Enum.IN__ID_DESBLOQUEIO.toString(), Types.INTEGER),
                        new SqlOutParameter(ParametrosProcDb2Enum.OUT__CD_RETORNO.toString(), Types.INTEGER),
                        new SqlOutParameter(ParametrosProcDb2Enum.OUT__CD_SQL_RET.toString(), Types.INTEGER));
    }

    @PostConstruct
    public void executarProcDb2() {
        System.out.println("ComSimpleJdbcCall");

        //SqlParameterSource inParams = new MapSqlParameterSource().addValue(ParametrosProcDb2Enum.IN__ID_DESBLOQUEIO.toString(), 1);
        var parametros = Map.of(ParametrosProcDb2Enum.IN__ID_DESBLOQUEIO.toString(), ID_ORDEM_DESBLOQUEIO);

        var retornoDaProc = simpleJdbcCall.execute(parametros);

        RetornoDaProcDTO retornoDaProcDTO = RetornoDaProcDTO.builder()
                .cdRetorno((Integer) retornoDaProc.get(ParametrosProcDb2Enum.OUT__CD_RETORNO.toString()))
                .cdSqlRet((Integer) retornoDaProc.get(ParametrosProcDb2Enum.OUT__CD_SQL_RET.toString()))
                .build();

        System.out.println(retornoDaProcDTO.getCdSqlRet());
        System.out.println(retornoDaProcDTO.getCdRetorno());
    }
}
