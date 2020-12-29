package com.github.rakhmedovrs.spring.jms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author RakhmedovRS
 * @created 12/29/2020
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HelloWorldMessage implements Serializable
{
	static final long serialVersionUID = 8955281857326878531L;

	private UUID id;
	private String message;
}
