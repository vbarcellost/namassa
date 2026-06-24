package br.com.ebac.app;

import br.com.ebac.dao.ClienteMapDAO;
import br.com.ebac.dao.IClienteDAO;
import br.com.ebac.domain.Cliente;

import javax.swing.JOptionPane;

public class App {

    private static final IClienteDAO CLIENTE_DAO = new ClienteMapDAO();

    public static void main(String[] args) {
        String opcao = "";

        while (!"5".equals(opcao)) {
            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastrar\n" +
                            "Digite 2 para consultar\n" +
                            "Digite 3 para excluir\n" +
                            "Digite 4 para alterar\n" +
                            "Digite 5 para sair",
                    "Cadastro de Cliente",
                    JOptionPane.INFORMATION_MESSAGE);

            if (opcao == null || "5".equals(opcao)) {
                sair();
                break;
            }

            switch (opcao) {
                case "1":
                    cadastrar();
                    break;
                case "2":
                    consultar();
                    break;
                case "3":
                    excluir();
                    break;
                case "4":
                    alterar();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida");
            }
        }
    }

    private static void cadastrar() {
        Cliente cliente = criarClienteAPartirDaTela();
        if (cliente == null) {
            return;
        }

        Boolean cadastrado = CLIENTE_DAO.cadastrar(cliente);
        if (cadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente ja cadastrado");
        }
    }

    private static void consultar() {
        String cpf = lerCpf();
        if (cpf == null) {
            return;
        }

        Cliente cliente = CLIENTE_DAO.consultar(cpf);
        if (cliente != null) {
            JOptionPane.showMessageDialog(null, cliente.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Cliente nao encontrado");
        }
    }

    private static void excluir() {
        String cpf = lerCpf();
        if (cpf == null) {
            return;
        }

        Cliente cliente = CLIENTE_DAO.consultar(cpf);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente nao encontrado");
            return;
        }

        CLIENTE_DAO.excluir(cpf);
        JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso");
    }

    private static void alterar() {
        String cpf = lerCpf();
        if (cpf == null) {
            return;
        }

        Cliente clienteCadastrado = CLIENTE_DAO.consultar(cpf);
        if (clienteCadastrado == null) {
            JOptionPane.showMessageDialog(null, "Cliente nao encontrado");
            return;
        }

        Cliente clienteAlterado = criarClienteAPartirDaTela(cpf);
        if (clienteAlterado == null) {
            return;
        }

        CLIENTE_DAO.alterar(clienteAlterado);
        JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");
    }

    private static Cliente criarClienteAPartirDaTela() {
        String cpf = lerCpf();
        if (cpf == null) {
            return null;
        }
        return criarClienteAPartirDaTela(cpf);
    }

    private static Cliente criarClienteAPartirDaTela(String cpf) {
        String nome = lerCampoObrigatorio("Digite o nome do cliente");
        String telefone = lerCampoObrigatorio("Digite o telefone do cliente");
        String endereco = lerCampoObrigatorio("Digite o endereco do cliente");
        String numero = lerCampoObrigatorio("Digite o numero do endereco");
        String cidade = lerCampoObrigatorio("Digite a cidade");
        String estado = lerCampoObrigatorio("Digite o estado");

        if (nome == null || telefone == null || endereco == null || numero == null || cidade == null || estado == null) {
            return null;
        }

        return new Cliente(nome, cpf, telefone, endereco, numero, cidade, estado);
    }

    private static String lerCpf() {
        return lerCampoObrigatorio("Digite o CPF do cliente");
    }

    private static String lerCampoObrigatorio(String mensagem) {
        String valor = JOptionPane.showInputDialog(null, mensagem, "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);

        if (valor == null) {
            return null;
        }

        valor = valor.trim();
        if (valor.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campo obrigatorio");
            return null;
        }

        return valor;
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Ate logo");
    }
}
