package br.com.caelum.fj91.microservices.rest;

import br.com.caelum.fj91.microservices.dtos.BookDTO;
import br.com.caelum.fj91.microservices.exceptions.NotFoundException;
import br.com.caelum.fj91.microservices.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksClient {

    private RestTemplate rest = new RestTemplate();
    private String baseURI = "http://localhost:8081/books";

    public Product getProductById(Long id) {

        URI uri = URI.create(baseURI+"/"+id);

        RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();

        ResponseEntity<BookDTO> response = rest.exchange(request, BookDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().toEntity();
        }

        throw new NotFoundException();
    }


    public List<Product> getAllProducts() {

        URI uri = URI.create(baseURI);

        RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();

        ResponseEntity<List<BookDTO>> response = rest.exchange(request, new ParameterizedTypeReference<List<BookDTO>>(){});

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().stream().map(BookDTO::toEntity).collect(Collectors.toList());
        }

        throw new NotFoundException();
    }
}
