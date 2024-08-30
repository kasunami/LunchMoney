import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      '/accountUsers': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
      '/transactions': {
        target: 'http://localhost:8080',
        changeOrigin: true,
      },
    },
  },
});