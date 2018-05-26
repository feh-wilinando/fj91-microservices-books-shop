package br.com.caelum.fj91.microservices.rest;

import br.com.caelum.fj91.microservices.dtos.BookDTO;
import br.com.caelum.fj91.microservices.exceptions.NotFoundException;
import br.com.caelum.fj91.microservices.models.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksClient {

    private final DiscoveryClient discovery;
    private RestTemplate rest = new RestTemplate();
    private String baseURI = "/books";

    public BooksClient(DiscoveryClient discovery) {
        this.discovery = discovery;
    }

    @Cacheable("product")
    @HystrixCommand(fallbackMethod = "fallbackById")
    public Product getProductById(Long id) {

        ServiceInstance instance = discovery.getInstances("books").stream().findAny().orElseThrow(() -> new RuntimeException("No instances available!"));

        URI uri = instance.getUri().resolve(baseURI+"/"+id);

        RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();

        ResponseEntity<BookDTO> response = rest.exchange(request, BookDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().toEntity();
        }

        throw new NotFoundException();
    }

    @CacheEvict(value = "product", allEntries = true)
    public Product fallbackById(Long id){
        throw new NotFoundException();
    }

    @Cacheable("products")
    @HystrixCommand(fallbackMethod = "fallbackAll")
    public List<Product> getAllProducts() {
        ServiceInstance instance = discovery.getInstances("books").stream().findAny().orElseThrow(() -> new RuntimeException("No instances available!"));

        URI uri = instance.getUri().resolve(baseURI);

        RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();

        ResponseEntity<List<BookDTO>> response = rest.exchange(request, new ParameterizedTypeReference<List<BookDTO>>(){});

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody().stream().map(BookDTO::toEntity).collect(Collectors.toList());
        }

        throw new NotFoundException();
    }

    @CacheEvict(value = "products", allEntries = true)
    public List<Product> fallbackAll(){
        System.out.println("\n\n\n\n");
        System.out.println("FALLBACK");
        System.out.println("\n\n\n\n");
        return new ArrayList<>();
    }
}
