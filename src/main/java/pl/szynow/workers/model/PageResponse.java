package pl.szynow.workers.model;

import lombok.Data;
import org.springframework.data.domain.Page;
import pl.szynow.workers.model.object.ConfigurationObject;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PageResponse<T> {

    private Long totalElements;
    private Integer totalPages;
    private Integer perPage;
    private Integer page;

    private List<T> elements;

    public PageResponse(Page page, List<T> elements) {
        this.elements = elements;
        this.page = page.getNumber();
        this.perPage = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }

}
