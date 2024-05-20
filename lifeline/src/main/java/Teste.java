import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.janelas.Janela;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import com.github.britooo.looca.api.group.processos.Processo;
import com.github.britooo.looca.api.group.sistema.Sistema;
import conexao.Conexao;
import maquina.Conversor;
import maquina.Limite;
import maquina.Maquina;
import maquina.Registro;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import usuario.Usuario;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Teste {
    public static void main(String[] args) {
        // SAO MEUS TESTE DOIDOS :)
//        Conexao conectar = new Conexao();
//        JdbcTemplate conec = conectar.getConexao();
//
//        try {
//            Maquina maquinaVerificacao = conec.queryForObject("SELECT * FROM maquina WHERE fkUsuario = ? AND macAddress = ?", new BeanPropertyRowMapper<>(Maquina.class), 1, "18:a6:f7:01:3c:2d");
//            if (maquinaVerificacao.getMacAddress() != null) {
//                System.out.println("Pegou algo");
//            }
//        } catch (EmptyResultDataAccessException e) {
//            System.out.println("vazio");
//        }

//        System.out.println(looca.getRede().getParametros().getHostName());

//        Double disco = 0.0;
//        for (int i = 0; i < looca.getGrupoDeDiscos().getVolumes().size(); i++) {
//            disco += Conversor.converterDoubleTresDecimais(Conversor.formatarBytes(looca.getGrupoDeDiscos().getVolumes().get(i).getTotal() - looca.getGrupoDeDiscos().getVolumes().get(i).getDisponivel()));
//        }
//        System.out.println(disco);
        //System.out.println(looca.getGrupoDeJanelas().getJanelasVisiveis());
        //looca.getGrupoDeJanelas().getJanelasVisiveis().get(1).getTitulo()
//        List<Processo> lista = looca.getGrupoDeProcessos().getProcessos().stream().filter(processo -> processo.getUsoCpu().doubleValue() > 0.5).toList();
//        System.out.println(lista);

//        List<Janela> lista2 = looca.getGrupoDeJanelas().getJanelasVisiveis().stream().filter(janela -> janela.getPid().equals(20020)).toList();
//        System.out.println(lista2);

//        Processo processoMaisConsome = looca.getGrupoDeProcessos().getProcessos().get(0);
//
//        for (Processo processo : looca.getGrupoDeProcessos().getProcessos()) {
//            if (processoMaisConsome.getBytesUtilizados() < processo.getBytesUtilizados()) {
//                processoMaisConsome = processo;
//            }
//        }
//
//        Janela janelaProcesso = looca.getGrupoDeJanelas().getJanelas().get(0);
//
//        for (Janela janela : looca.getGrupoDeJanelas().getJanelas()) {
//            if (processoMaisConsome.getPid() == janela.getPid().intValue()) {
//                janelaProcesso = janela;
//            }
//        }
//
//        System.out.println(janelaProcesso.getTitulo());

//        System.out.println(looca.getTemperatura().getTemperatura().doubleValue());

        // PULANDO LOGIN
//        Usuario usuario = new Usuario("joao@techinnovations.com", "senha123");
//        Maquina maquina = new Maquina();
//
//        maquina.verificarMaquina(usuario.getIdUsuario());
                try {
                    Looca looca = new Looca();
                    Sistema sistema = looca.getSistema();

                    if (sistema != null) {
                        long tempoAtividade = sistema.getTempoDeAtividade();
                        System.out.printf("Tempo de uso: %d dias, %d horas, %d minutos, %d segundos\n",
                                TimeUnit.MILLISECONDS.toDays(tempoAtividade),
                                TimeUnit.MILLISECONDS.toHours(tempoAtividade) % 24,
                                TimeUnit.MILLISECONDS.toMinutes(tempoAtividade) % 60,
                                TimeUnit.MILLISECONDS.toSeconds(tempoAtividade) % 60);
                    } else {
                        System.out.println("Não foi possível obter o tempo de atividade do sistema.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao inicializar Looca: " + e.getMessage());
                }
            }
        }
