package api.dribble;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by bhaskar on 2014-08-25.
 */
@Module(
        injects = DribbbleService.class,
        library = true
)
public class DribbbleServiceModule {
    final String endpointUrl;

    public DribbbleServiceModule(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    @Singleton
    @Provides
    DribbbleService provideDribbbleService() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    private DateFormat format = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss Z");

                    @Override
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        String s = json.getAsJsonPrimitive().getAsString();
                        try {
                            return format.parse(s);
                        } catch (ParseException ignore) {
                        }

                        return null;
                    }
                })
                .create();

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(endpointUrl)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.BASIC)
                .build();

        return adapter.create(DribbbleService.class);
    }
}
