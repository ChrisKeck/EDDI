package ai.labs.resources.rest.behavior;

import ai.labs.resources.rest.IRestVersionInfo;
import ai.labs.resources.rest.behavior.model.BehaviorConfiguration;
import ai.labs.resources.rest.documentdescriptor.model.DocumentDescriptor;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author ginccc
 */
@Api(value = "configurations")
@Path("/behaviorstore/behaviorsets")
public interface IRestBehaviorStore extends IRestVersionInfo {
    String resourceURI = "eddi://ai.labs.behavior/behaviorstore/behaviorsets/";
    String versionQueryParam = "?version=";

    @GET
    @Path("descriptors")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filter", format = "string", example = "<name_of_behavior>"),
            @ApiImplicitParam(name = "index", format = "integer", example = "<at what position should the paging start>"),
            @ApiImplicitParam(name = "limit", format = "integer", example = "<how many results should be returned>")})
    @ApiResponse(code = 200, response = DocumentDescriptor.class, responseContainer = "List",
            message = "Array of DocumentDescriptors")
    @Produces({MediaType.APPLICATION_JSON, "text/yaml"})
    List<DocumentDescriptor> readBehaviorDescriptors(@QueryParam("filter") @DefaultValue("") String filter,
                                                     @QueryParam("index") @DefaultValue("0") Integer index,
                                                     @QueryParam("limit") @DefaultValue("20") Integer limit);

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, "text/yaml"})
    BehaviorConfiguration readBehaviorRuleSet(@PathParam("id") String id,
                                              @ApiParam(name = "version", required = true, format = "integer", example = "1")
                                 @QueryParam("version") Integer version);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateBehaviorRuleSet(@PathParam("id") String id,
                                   @ApiParam(name = "version", required = true, format = "integer", example = "1")
                              @QueryParam("version") Integer version, BehaviorConfiguration behaviorConfiguration);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response createBehaviorRuleSet(BehaviorConfiguration behaviorConfiguration);

    @DELETE
    @Path("/{id}")
    Response deleteBehaviorRuleSet(@PathParam("id") String id,
                                   @ApiParam(name = "version", required = true, format = "integer", example = "1")
                               @QueryParam("version") Integer version);
}
