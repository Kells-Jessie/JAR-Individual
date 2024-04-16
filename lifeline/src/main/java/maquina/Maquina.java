package maquina;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import com.github.britooo.looca.api.group.memoria.Memoria;
import com.github.britooo.looca.api.group.processador.Processador;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import conexao.Conexao;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class Maquina {
    Conexao conectar = new Conexao();
    JdbcTemplate conec = conectar.getConexao();
    Looca looca = new Looca();
    private Integer idMaquina;
    private String macAddress;
    private String ip;
    private String sistemaOperacional;
    private Double maxCpu;
    private Double maxRam;
    private Double maxDisco;

    public Maquina() {
        List<RedeInterface> listaRede = looca.getRede().getGrupoDeInterfaces().getInterfaces().stream().filter(redeInterface -> !redeInterface.getEnderecoIpv4().isEmpty()).toList();

        this.ip = listaRede.get(0).getEnderecoIpv4().get(0);
        this.sistemaOperacional = looca.getSistema().getSistemaOperacional();
        this.maxCpu = Conversor.converterFrequencia(looca.getProcessador().getFrequencia());
        this.maxRam = Conversor.converterDoubleDoisDecimais(Conversor.formatarBytes(looca.getMemoria().getTotal()));
        this.maxDisco = Conversor.converterDoubleTresDecimais(Conversor.formatarBytes(looca.getGrupoDeDiscos().getTamanhoTotal()));
    }

    public void cadastrarMaquina(String email, String nome, Integer idUsuario) {
        Maquina maquina = new Maquina();
        try {
            conec.queryForObject("SELECT m.idMaquina FROM maquina m JOIN usuario u ON m.fkUsuario = u.idUsuario WHERE u.email = ? and m.nome = ?", Integer.class, email, nome);

            System.out.println("Você já cadastrou as informações do hardware no sistema!");
        } catch (EmptyResultDataAccessException e) {
            conec.update("INSERT INTO maquina (nome, ip, sistemaOperacional, maxCpu, maxRam, maxDisco, fkUsuario) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    nome, maquina.getIp(), maquina.getSistemaOperacional(), maquina.getMaxCpu(), maquina.getMaxRam(), maquina.getMaxDisco(), idUsuario);
            conec.update("""
                    INSERT INTO limitador (fkMaquina, limiteCpu, limiteRam, limiteDisco)
                                        SELECT
                                            idMaquina,
                                            maxCpu * 0.8,  -- Reduzindo o limite de CPU em 20%
                                            maxRam * 0.8,  -- Reduzindo o limite de RAM em 20%
                                            maxDisco * 0.8  -- Reduzindo o limite de disco em 20%
                                        FROM maquina where fkUsuario = ? ORDER BY idMaquina DESC limit 1
                    """, idUsuario);
            System.out.println("Cadastrey");
        }
    }


    //Getter
    public String getIp() {
        return ip;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public Double getMaxCpu() {
        return maxCpu;
    }

    public Double getMaxRam() {
        return maxRam;
    }

    public Double getMaxDisco() {
        return maxDisco;
    }
}