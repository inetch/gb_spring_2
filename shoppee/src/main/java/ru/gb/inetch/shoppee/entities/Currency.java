package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "com_currency")
@Data
@NoArgsConstructor
public class Currency {
    @Id
    @Column(name = "code")
    private String code;

    @NotNull(message = "Char code cannot be null")
    @Size(min = 3, max = 3, message = "Char code must be exactly 3 symbols")
    @Column(name = "char_code")
    private String charCode;

    @NotNull(message = "Currency symbol cannot be null")
    @Column(name = "currency_symbol")
    private Character currencySymbol;

    @NotNull(message = "Exponent cannot be null")
    @Column(name = "exponent")
    private Integer exponent;
}
