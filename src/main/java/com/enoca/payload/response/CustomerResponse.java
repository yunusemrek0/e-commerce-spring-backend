package com.enoca.payload.response;

import com.enoca.payload.response.abstractresponse.BaseAbstractResponse;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CustomerResponse extends BaseAbstractResponse {

    private Long id;

    private String username;

    private String name;

    private String lastName;

    private String email;

}
