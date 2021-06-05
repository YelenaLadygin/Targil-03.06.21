package com.example.demo;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

    @RestController
    public class RestaurantController {

        private static List<RestaurantDTO> restaurant= new ArrayList<>();

        // static ctor
        static {
            restaurant.add( new RestaurantDTO(1, "japanika","rehovot","sushi",22) );
            restaurant.add( new RestaurantDTO(2, "nafis","rishon le tzion","steak",56) ) ;
            restaurant.add( new RestaurantDTO(3, "humburg","tel aviv","humburger",10)  );
        }

        @GetMapping("/restaurant")
        public List<RestaurantDTO> getRestaurant()
        {
            return restaurant;
        }

        @GetMapping("/restaurant/{id}")
        public RestaurantDTO doGetrestaurantById(@PathVariable("id") int id)
        {
            for(RestaurantDTO c : restaurant)
            {
                if (c.getId() == id)
                {
                    c.setName(c.getName());
                    return c;
                }
            }
            return null;
        }

        @PostMapping("/restaurant")
        public ResponseEntity<String> addrestaurant
                (@RequestBody RestaurantDTO c)
        {
            restaurant.add(c);
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>(
                    "Custom header set", headers, HttpStatus.CREATED);
        }

        @PutMapping("/restaurant/{id}")
        public void updaterestaurant
                (@PathVariable("id") int id, @RequestBody RestaurantDTO update_c)
        {
            for(RestaurantDTO c : restaurant)
            {
                if (c.getId() == id)
                {
                    c.setId(update_c.getId());
                    c.setName(update_c.getName());
                    c.setFoodtype(update_c.getFoodtype());
                    c.setMealprice(update_c.getMealprice());
                    return;
                }
            }
        }

        @DeleteMapping("/restaurant/{id}")
        public void delCouponById(@PathVariable("id") int id)
        {
            Iterator<RestaurantDTO>iter = restaurant.iterator();
            while (iter.hasNext()) {
                RestaurantDTO c = iter.next();
                if (c.getId() == id) {
                    iter.remove();
                    return;
                }
            }
        }

    }

