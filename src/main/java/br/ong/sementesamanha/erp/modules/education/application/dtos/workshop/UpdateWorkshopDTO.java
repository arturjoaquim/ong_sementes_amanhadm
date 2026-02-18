package br.ong.sementesamanha.erp.modules.education.application.dtos.workshop;

public record UpdateWorkshopDTO (
		String name,
		Integer enrollmentLimit,
		Boolean active
)
{}
