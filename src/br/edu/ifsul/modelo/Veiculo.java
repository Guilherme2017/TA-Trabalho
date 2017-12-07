package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_veiculo", sequenceName = "seq_veiculo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_veiculo", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "A descricao não pode ser nula")
    @NotBlank(message = "A descricao nao pode estar em branco")
    @Length(max = 50, message = "A descricao não pode ter mais que {max} caracteres")
    @Column(name = "descricao", length = 50, nullable = false)
    private String descricao;
    @NotNull(message = "A placa não pode ser nula")
    @NotBlank(message = "A placa nao pode estar em branco")
    @Length(max = 10, message = "A placa não pode ter mais que {max} caracteres")
    @Column(name = "placa", length = 10, nullable = false)
    private String placa;
    @NotNull(message = "A cor não pode ser nulo")
    @NotBlank(message = "A cor nao pode estar em branco")
    @Length(max = 50, message = "A cor não pode ter mais que {max} caracteres")
    @Column(name = "cor", length = 50, nullable = false)
    private String cor;
    @ManyToOne
    @JoinColumn(name = "cliente", referencedColumnName = "id",
            nullable = false, foreignKey = @ForeignKey(name = "fk_cliente_id"))
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "mecanico1", referencedColumnName = "id",
            nullable = false, foreignKey = @ForeignKey(name = "fk_mecanico1_id"))
    private Mecanico1 mecanico1;
    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Ordem_Servico> ordem = new ArrayList<>();

    public Veiculo() {
    }
    
    public void adicionarOrdem(Ordem_Servico obj){
        obj.setVeiculo(this);
        this.ordem.add(obj);
    }
    
    public void removerOrdem(int idx){
        this.ordem.remove(idx);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mecanico1 getMecanico1() {
        return mecanico1;
    }

    public void setMecanico1(Mecanico1 mecanico1) {
        this.mecanico1 = mecanico1;
    }

    public List<Ordem_Servico> getOrdem() {
        return ordem;
    }

    public void setOrdem(List<Ordem_Servico> ordem) {
        this.ordem = ordem;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Veiculo other = (Veiculo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
