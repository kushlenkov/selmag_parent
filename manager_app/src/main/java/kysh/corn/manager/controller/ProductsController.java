package kysh.corn.manager.controller;

import kysh.corn.manager.client.BadRequestException;
import kysh.corn.manager.entity.Product;
import kysh.corn.manager.client.ProductsRestClient;
import kysh.corn.manager.controller.payload.NewProductPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

    private final ProductsRestClient productsRestClient;

    @GetMapping("list")
    public String getProductsList(Model model) {

        model.addAttribute("products", this.productsRestClient.findAllProducts());

        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getNewProductPage() {

        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload payload,
                                Model model) {

        try {
            Product product = this.productsRestClient.createProduct(payload.title(), payload.details());

            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException exception) {

            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());

            return "catalogue/products/new_product";
        }
    }
}
