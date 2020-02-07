package dagger.internal;

import dagger.Lazy;
import java.util.Map;
import javax.inject.Provider;

public final class MapProviderFactory<K, V> extends AbstractMapFactory<K, V, Provider<V>> implements Lazy<Map<K, Provider<V>>> {

    public static final class Builder<K, V> extends dagger.internal.AbstractMapFactory.Builder<K, V, Provider<V>> {
        private Builder(int i) {
            super(i);
        }

        public Builder<K, V> put(K k, Provider<V> provider) {
            super.put(k, provider);
            return this;
        }

        public Builder<K, V> putAll(Provider<Map<K, Provider<V>>> provider) {
            super.putAll(provider);
            return this;
        }

        public MapProviderFactory<K, V> build() {
            return new MapProviderFactory<>(this.map);
        }
    }

    public static <K, V> Builder<K, V> builder(int i) {
        return new Builder<>(i);
    }

    private MapProviderFactory(Map<K, Provider<V>> map) {
        super(map);
    }

    public Map<K, Provider<V>> get() {
        return contributingMap();
    }
}
