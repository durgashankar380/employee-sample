package dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeePageRequestDto {
	
	private Integer pageNo = 0;
	private Integer pageSize = 10;
	private Sort.Direction sort = Sort.Direction.ASC;
	private String sortByColumn = "id";

	private Integer status;

	private String searchKeyword;
	private Long id;
	private Double salary;
	private String department;
	
	public Pageable getPageable() {
		return PageRequest.of(pageNo, pageSize,Sort.by(sort,sortByColumn));
	}
}
