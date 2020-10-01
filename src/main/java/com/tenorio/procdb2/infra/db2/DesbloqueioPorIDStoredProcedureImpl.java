package com.tenorio.procdb2.infra.db2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.Map;

@Qualifier("desbloqueioPorIDStoredProcedureImpl")
@Component
public class DesbloqueioPorIDStoredProcedureImpl extends StoredProcedure {

    @Autowired
    public DesbloqueioPorIDStoredProcedureImpl(JdbcTemplate jdbcTemplate, @Value("${procedure.desbloqueio}") String nome) {
        super(jdbcTemplate, nome);

        // Parametros de entrada
        declareParameter(new SqlParameter(ParametrosProcDb2Enum.IN__ID_DESBLOQUEIO.toString(), Types.INTEGER));

        // Parametros de saida
        declareParameter(new SqlOutParameter(ParametrosProcDb2Enum.OUT__CD_RETORNO.toString(), Types.INTEGER));
        declareParameter(new SqlOutParameter(ParametrosProcDb2Enum.OUT__CD_SQL_RET.toString(), Types.INTEGER));

        setFunction(false);
        compile();
    }

    public RetornoDaProcDTO executarProcDb2(Integer idOrdemDesbloqueio) {
        var parametros = Map.of(ParametrosProcDb2Enum.IN__ID_DESBLOQUEIO.toString(), idOrdemDesbloqueio);

        var retornoDaProc = execute(parametros);

        return RetornoDaProcDTO.builder()
                .cdRetorno((Integer) retornoDaProc.get(ParametrosProcDb2Enum.OUT__CD_RETORNO.toString()))
                .cdSqlRet((Integer) retornoDaProc.get(ParametrosProcDb2Enum.OUT__CD_SQL_RET.toString()))
                .build();
    }

}