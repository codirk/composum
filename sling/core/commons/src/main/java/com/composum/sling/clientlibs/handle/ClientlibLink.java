package com.composum.sling.clientlibs.handle;

import com.composum.sling.clientlibs.processor.RendererContext;
import com.composum.sling.core.util.LinkUtil;
import org.apache.sling.api.resource.Resource;

import java.util.HashMap;
import java.util.Map;

import static com.composum.sling.clientlibs.handle.Clientlib.Type.img;
import static com.composum.sling.clientlibs.handle.Clientlib.Type.link;

public class ClientlibLink extends ClientlibKey {

    public ClientlibLink(final Clientlib clientlib) {
        this(clientlib.clientlibRef, clientlib.resource);
    }

    public ClientlibLink(final ClientlibRef reference, final Resource resource) {
        this(reference.type, resource, reference.properties);
    }

    public ClientlibLink(final ClientlibRef reference, final Resource resource,
                         final Map<String,String> properties) {
        this(reference.type, resource, properties);
    }

    public ClientlibLink(final Clientlib.Type type, final Resource resource) {
        this(type, resource, new HashMap<String, String>());
    }

    public ClientlibLink(final Clientlib.Type type, final Resource resource,
                         Map<String, String> properties) {
        super(type, resource.getPath(), properties);
    }

    public String getUrl(RendererContext context) {
        StringBuilder builder = new StringBuilder(path);
        if (!path.endsWith("." + type.name()) && type != img && type != link) {
            builder.append('.').append(type.name());
        }
        String url;
        if (context.mapClientlibURLs()) {
            url = LinkUtil.getUrl(context.request, builder.toString());
        } else {
            url = LinkUtil.getUnmappedUrl(context.request, builder.toString());
        }
        return url;
    }
}
