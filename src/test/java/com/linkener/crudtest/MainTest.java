package com.linkener.crudtest;

import com.linkener.crudtest.constant.TestConstant;
import com.linkener.crudtest.util.CommonTestUtil;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;

public class MainTest extends BaseTest {

    @Test
    public void buscar() throws Exception {
        final MockHttpServletResponse response = this.mvc.perform(
                get("/testentity")
                        .accept(MediaType.APPLICATION_JSON)
                        .flashAttr("searchFilter", buildTestEntity("test_ent"))
                        .param("page", String.valueOf(TestConstant.PAGE))
                        .param("size", String.valueOf(TestConstant.SIZE))
                        .param("order", TestConstant.ORDER)
        )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(
                jsonList.write(toList(getTestEntity("test"))).getJson()
        );
    }

    @Test
    public void crear() throws Exception {
        final MockHttpServletResponse response = this.mvc.perform(
                post("/testentity")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(CommonTestUtil.toJson(buildTestEntity("test_new")))
        )
                .andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(
                MockMvcResultMatchers.jsonPath("$.name").value("test_new")
        );
    }

    @Test
    public void modificar() throws Exception {
        final MockHttpServletResponse response = this.mvc.perform(
                RestDocumentationRequestBuilders.put("/testentity/{id}", getTestEntity("test").getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(CommonTestUtil.toJson(buildTestEntity("test_edit")))
        )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(
                jsonTestEntity.write(buildTestEntity("test_edit")).getJson()
        );
    }

    @Test
    public void eliminar() throws Exception {
        final MockHttpServletResponse response = this.mvc.perform(
                delete("/testentity/{id}", getTestEntity("test").getId())
        )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void buscarUno() throws Exception {
        final MockHttpServletResponse response = this.mvc.perform(
                get("/testentity/{id}", getTestEntity("test").getId())
        )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString(StandardCharsets.UTF_8)).isEqualTo(
                jsonTestEntity.write(getTestEntity("test")).getJson()
        );
    }
}
