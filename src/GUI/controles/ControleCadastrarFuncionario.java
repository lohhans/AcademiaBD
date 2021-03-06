package GUI.controles;

import GUI.Main;
import GUI.telasDeAlerta.*;
import fachada.Fachada;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import negocios.exception.*;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ControleCadastrarFuncionario implements Initializable {

    private Fachada fachada;

    public ControleCadastrarFuncionario(){
        this.fachada = Fachada.getInstancia();
    }

    private Stage tela;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label labelNome, labelSexo, labelDataDeNascimento, labelCPF, labelTelefone, labelCEP, labelNumero, labelRua, labelBairro, labelCidade, labelGerente, labelSenha;

    @FXML
    private TextField nome, sexo, dataDeNascimento, cpf, telefone, cep, numero, rua, bairro, cidade, gerente, senha;

    @FXML
    private void acaoCadastrarFuncionario(ActionEvent event){
        try {
            //Conversao de String data para Date
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            Date dataFormatada = formato.parse(dataDeNascimento.getText());

            fachada.cadastrarFuncionario(nome.getText(), sexo.getText(), dataFormatada, cpf.getText(), telefone.getText(), cep.getText(), numero.getText(), rua.getText(), bairro.getText(), cidade.getText(), senha.getText());

            CadastroConcluidoAlerta c = new CadastroConcluidoAlerta();
            c.alerta();

            acaoBotaoVoltar(event);
        } catch (PessoaJaCadastradaException e) {
            PessoaJaCadastradaAlerta p = new PessoaJaCadastradaAlerta();
            p.alerta();
        } catch (DadosInvalidosException e) {
            DadosInvalidosAlerta d = new DadosInvalidosAlerta();
            d.alerta();
        } catch (CpfInvalidoException e) {
            CpfInvalidoAlerta c = new CpfInvalidoAlerta();
            c.alerta();
        } catch (CepInvalidoException e) {
            CepInvalidoAlerta c = new CepInvalidoAlerta();
            c.alerta();
        } catch (SexoIncorretoException e) {
            SexoIncorretoAlerta s = new SexoIncorretoAlerta();
            s.alerta();
        } catch (ParseException e) {
            DataErradaAlerta d = new DataErradaAlerta();
            d.alerta();
        }
    }

    @FXML
    private void acaoBotaoVoltar(ActionEvent event){
        Main.chamarTela("fxml/GuiMenuGerente.fxml", 800, 600);
        this.tela = (Stage) this.pane.getScene().getWindow();
        tela.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
