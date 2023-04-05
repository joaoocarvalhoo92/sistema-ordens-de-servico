/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.jpsoftware.telas;

/**
 *
 * @author joao1
 */

import java.sql.*;
import br.com.jpsoftware.dal.ModuloConexao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
// a linha abaixo importa recursos da biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;
public class TelaCliente extends javax.swing.JInternalFrame {
Connection conexao = null;
PreparedStatement pst =null;
ResultSet rs = null;


    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
        initComponents();
         conexao=ModuloConexao.conector();
    }
    
    
     // Método para adicionar clientes no banco de dados mysql
    private void adicionar(){
        String sql = "insert into dbclientes(nomeCliente,enderecoCliente,foneCliente,emailCliente,cpfCliente,cidadeCliente,estadoCliente,cepCliente)values(?,?,?,?,?,?,?,?)";
             
        try{
            
             pst = conexao.prepareStatement(sql);
             pst.setString(1,txtCliNome.getText());
             pst.setString(2,txtCliEndereco.getText());
             pst.setString(3,txtCliTelefone.getText());
             pst.setString(4,txtCliEmail.getText());
             pst.setString(5,txtCliCpf.getText());
             pst.setString(6,txtCliCidade.getText());
             pst.setString(7,txtCliUf.getText());
             pst.setString(8,txtCliCep.getText());
             
             
             // Validação dos campos obrigatórios
             
             if (txtCliNome.getText().isEmpty()||
                 txtCliEndereco.getText().isEmpty()||
                 txtCliTelefone.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios");
                
            } else {
                 
       
            
             // a linha abaixo atualiza os valores na tabela usuario com os dados do formulario
             // a estrutura abaixo é usada para confirmar a inserção dos dados na tabela
             int adicionado = pst.executeUpdate();
             // a saida da variavel "adicionado" vai ser 1 ou zero
             // 1 para adicionado
             // 0 para não adicionado
             if(adicionado > 0) {
                 JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso");
                 limpar();

               
             }
        }
             } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
        }
    }
    
     // metodo para consultar clientes pelo nome com filtro
    private void pequisar_clientes(){
        String sql="select idCliente as id, nomeCliente as Nome, enderecoCliente as Endereço, foneCliente as Telefone, emailCliente as Email, cpfCliente as CPF, cidadeCliente as Cidade, estadoCliente as Estado, cepCliente as Cep from dbclientes where nomeCliente like ?";
        
        try {
            pst = conexao.prepareStatement(sql);
            // passando o conteudo da caixa de pesquisa para o "?";
            // atenção ao % que é a continuação da string sql
            pst.setString(1,txtCliPesquisar.getText() + "%");
            rs=pst.executeQuery();
            // a linha abaixo usa a bibliote rs2xml.rar para preencher a tabela
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            
            
        }
    }
    
    // metodo para setar os campos do formulario com o conteudo da tabela
    public void setar_campos(){
        int setar = tblClientes.getSelectedRow();
                txtCliId.setText(tblClientes.getModel().getValueAt(setar,0).toString());

        txtCliNome.setText(tblClientes.getModel().getValueAt(setar,1).toString());
         txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar,2).toString());
          txtCliTelefone.setText(tblClientes.getModel().getValueAt(setar,3).toString());
           txtCliEmail.setText(tblClientes.getModel().getValueAt(setar,4).toString());
            txtCliCpf.setText(tblClientes.getModel().getValueAt(setar,5).toString());
             txtCliCidade.setText(tblClientes.getModel().getValueAt(setar,6).toString());
              txtCliUf.setText(tblClientes.getModel().getValueAt(setar,7).toString());
               txtCliCep.setText(tblClientes.getModel().getValueAt(setar,8).toString());
        
        // a linha abaixo desabilita o botao adicionar para nao haver duplicidade
       btnCliCreate.setEnabled(false);
        
        
    }
    
    // criando o metodo para alterar dados do cliente
    private void alterar(){
        String sql = "update dbclientes set nomeCliente=?,enderecoCliente=?,foneCliente=?,emailCliente=?,cpfCliente=?,cidadeCliente=?,estadoCliente=?,cepCliente=? where idCliente=?";
        
        try {
            
            pst = conexao.prepareStatement(sql);
            pst.setString(1,txtCliNome.getText());
            pst.setString(2,txtCliEndereco.getText());
            pst.setString(3,txtCliTelefone.getText());
            pst.setString(4,txtCliEmail.getText());
            pst.setString(5,txtCliCpf.getText());
            pst.setString(6,txtCliCidade.getText());
            pst.setString(7,txtCliUf.getText());
            pst.setString(8,txtCliCep.getText());
            pst.setString(9,txtCliId.getText());
            
            
            // Validação dos campos obrigatórios
             
             if (txtCliNome.getText().isEmpty()||
                 txtCliEndereco.getText().isEmpty()||
                 txtCliTelefone.getText().isEmpty()) {
                 JOptionPane.showMessageDialog(null,"Preencha todos os campos obrigatórios");
                
            } else {

            
             // a linha abaixo atualiza os valores na tabela usuario com os dados do formulario
             // a estrutura abaixo é usada para confirmar a alteração dos dados na tabela
             int adicionado = pst.executeUpdate();
             // a saida da variavel "adicionado" vai ser 1 ou zero
             // 1 para adicionado
             // 0 para não adicionado
             if(adicionado > 0) {
                 JOptionPane.showMessageDialog(null,"Dados do cliente alterados com sucesso");
                        limpar();
                        
                        btnCliCreate.setEnabled(true);
          
               
             }
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        
        
    }
    
    
        // Metodo responsavel pela remoção de usuarios
    private void remover(){
        //a estrutura abaixo confirma a remoção do usuario
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuario?","Atenção",JOptionPane.YES_NO_OPTION);
        if(confirma ==JOptionPane.YES_NO_OPTION){
            String sql = "delete from dbclientes where idCliente=?";
            
            try {
                pst=conexao.prepareStatement(sql);
                pst.setString(1,txtCliId.getText());
                int apagado = pst.executeUpdate();
                if(apagado>0){
                    JOptionPane.showMessageDialog(null,"Cliente removido com sucesso");
                    
                     limpar();
                     btnCliCreate.setEnabled(true);
                     
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
            }
        }
    
    }
    
    // metodo para limpar os campos do formulario
    private void limpar(){
               txtCliPesquisar.setText(null);
                txtCliId.setText(null);
                txtCliNome.setText(null);
                txtCliEndereco.setText(null);
                txtCliTelefone.setText(null);
                txtCliEmail.setText(null);
                txtCliCpf.setText(null);
                txtCliCidade.setText(null);
                txtCliUf.setText(null);
                txtCliCep.setText(null);
                ((DefaultTableModel) tblClientes.getModel()).setRowCount(0);
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtCliPesquisar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliTelefone = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        txtCliCpf = new javax.swing.JTextField();
        txtCliCidade = new javax.swing.JTextField();
        txtCliCep = new javax.swing.JTextField();
        txtCliUf = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btnCliCreate = new javax.swing.JButton();
        btnCliUpdate = new javax.swing.JButton();
        btnCliRemover = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Clientes");

        jLabel1.setText("* Campos Obrigatórios");

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        tblClientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nome", "Endereço", "Telefone", "Email", "CPF", "Cidade", "Estado", "Cep"
            }
        ));
        tblClientes.setFocusable(false);
        tblClientes.getTableHeader().setReorderingAllowed(false);
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        tblClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblClientesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel2.setText("Id Cliente");

        txtCliId.setEnabled(false);

        jLabel3.setText("*Nome");

        jLabel4.setText("*Endereço");

        jLabel5.setText("*Telefone");

        jLabel6.setText("Email");

        jLabel7.setText("CPF");

        jLabel8.setText("Cidade");

        jLabel9.setText("UF");

        jLabel10.setText("Cep");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/jpsoftware/icones/pesquisar.png"))); // NOI18N

        btnCliCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/jpsoftware/icones/create.png"))); // NOI18N
        btnCliCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliCreate.setMaximumSize(new java.awt.Dimension(80, 80));
        btnCliCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliCreateActionPerformed(evt);
            }
        });

        btnCliUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/jpsoftware/icones/update.png"))); // NOI18N
        btnCliUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliUpdate.setMaximumSize(new java.awt.Dimension(80, 80));
        btnCliUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliUpdateActionPerformed(evt);
            }
        });

        btnCliRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/jpsoftware/icones/delete.png"))); // NOI18N
        btnCliRemover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliRemover.setMaximumSize(new java.awt.Dimension(80, 80));
        btnCliRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                                .addComponent(jLabel1)))
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCliTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtCliCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCliCpf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCliUf, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtCliCep, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtCliEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                                        .addComponent(txtCliNome, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCliEndereco, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnCliCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(101, 101, 101)
                                        .addComponent(btnCliUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(68, 68, 68)
                                        .addComponent(btnCliRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(jLabel3))
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCliTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtCliCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(txtCliCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliUf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCliCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCliCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCliUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCliRemover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
        );

        setBounds(0, 0, 800, 800);
    }// </editor-fold>//GEN-END:initComponents

    private void tblClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblClientesKeyReleased
        // // chamando metodo pesquisar
        pequisar_clientes();
    }//GEN-LAST:event_tblClientesKeyReleased

    private void btnCliUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliUpdateActionPerformed
        // chamando o metodo para alterar clientes
        alterar();
    }//GEN-LAST:event_btnCliUpdateActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        pequisar_clientes();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void btnCliCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliCreateActionPerformed
       adicionar();
    }//GEN-LAST:event_btnCliCreateActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnCliRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliRemoverActionPerformed
        remover();
    }//GEN-LAST:event_btnCliRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliCreate;
    private javax.swing.JButton btnCliRemover;
    private javax.swing.JButton btnCliUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliCep;
    private javax.swing.JTextField txtCliCidade;
    private javax.swing.JTextField txtCliCpf;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtCliTelefone;
    private javax.swing.JTextField txtCliUf;
    // End of variables declaration//GEN-END:variables

    private void setRow(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
