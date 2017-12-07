package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "ordem_servico")
public class Ordem_Servico implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_ordem_servico", sequenceName = "seq_ordem_servico_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_ordem_servico", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "A data de início não pode ser nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "inicio", nullable = false)
    private Calendar inicio;
    @NotNull(message = "A data de conclusao não pode ser nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "conclusao", nullable = false)
    private Calendar conclusao;
    @NotNull(message = "O tipo não pode ser nulo")
    @NotBlank(message = "O tipo nao pode estar em branco")
    @Length(max = 50, message = "O tipo não pode ter mais que {max} caracteres")
    @Column(name = "cor", length = 50, nullable = false)
    private String tipo;
    @NotNull(message = "O veiculo não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "veiculo", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_veiculo_ordem_servico"))
    private Veiculo veiculo;

    public Ordem_Servico() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getInicio() {
        return inicio;
    }

    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    public Calendar getConclusao() {
        return conclusao;
    }

    public void setConclusao(Calendar conclusao) {
        this.conclusao = conclusao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Ordem_Servico other = (Ordem_Servico) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
