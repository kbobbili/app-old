package com.kalbob.reactive.play.rest;

import static com.kalbob.reactive.play.rest.ItemConstants.ITEM_FUNCTIONAL_END_POINT_V1;
import static com.kalbob.reactive.play.rest.ItemConstants.ITEM_STREAM_FUNCTIONAL_END_POINT_V1;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ItemsRouter {

    @Bean
    public RouterFunction<ServerResponse> itemsRoute(ItemsHandler itemsHandler){

        return RouterFunctions
                .route(GET(ITEM_FUNCTIONAL_END_POINT_V1).and(accept(APPLICATION_JSON))
                ,itemsHandler::getAllItems)
                .andRoute(GET(ITEM_FUNCTIONAL_END_POINT_V1+"/{id}").and(accept(APPLICATION_JSON))
                ,itemsHandler::getOneItem)
                .andRoute(POST(ITEM_FUNCTIONAL_END_POINT_V1).and(accept(APPLICATION_JSON))
                ,itemsHandler::createItem)
                .andRoute(DELETE(ITEM_FUNCTIONAL_END_POINT_V1+"/{id}").and(accept(APPLICATION_JSON))
                        ,itemsHandler::deleteItem)
                .andRoute(PUT(ITEM_FUNCTIONAL_END_POINT_V1+"/{id}").and(accept(APPLICATION_JSON))
                        ,itemsHandler::updateItem);
    }

    @Bean
    public RouterFunction<ServerResponse> errorRoute(ItemsHandler itemsHandler){
        return RouterFunctions
                .route(GET("/fun/runtimeexception").and(accept(APPLICATION_JSON))
                        ,itemsHandler::itemsEx);

    }

    @Bean
    public RouterFunction<ServerResponse> itemStreamRoute(ItemsHandler itemsHandler){

        return RouterFunctions
                .route(GET(ITEM_STREAM_FUNCTIONAL_END_POINT_V1).and(accept(APPLICATION_JSON))
                        ,itemsHandler::itemsStream);

    }

}