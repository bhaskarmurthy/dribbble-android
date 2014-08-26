package api.dribble;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
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
                .registerTypeAdapter(Date.class, new TypeAdapter<Date>() {
                    private DateFormat format = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss Z");

                    @Override
                    public void write(JsonWriter out, Date value) throws IOException {
                        if (value == null) {
                            out.nullValue();
                            return;
                        }

                        String dateFormatAsString = format.format(value);
                        out.value(dateFormatAsString);
                    }

                    @Override
                    public Date read(JsonReader in) throws IOException {
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        }

                        try {
                            return format.parse(in.nextString());
                        } catch (ParseException ignored) {
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
