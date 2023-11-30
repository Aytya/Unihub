package com.example.project.web.mapper;
import com.example.project.domain.model.FinanceCabinet;
import com.example.project.web.dto.FinanceDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FinanceMapper extends Mappable<FinanceCabinet, FinanceDto> {
}
