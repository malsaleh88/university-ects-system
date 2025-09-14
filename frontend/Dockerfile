# =====================
# 1. Build stage
# =====================
FROM node:20-alpine AS build
WORKDIR /app

# Copy dependency definitions
COPY package*.json ./

# Install dependencies
RUN npm ci

# Copy source code and build
COPY . .
RUN npm run build

# =====================
# 2. Run stage
# =====================
FROM nginx:stable-alpine
WORKDIR /usr/share/nginx/html

# Copy built React app
COPY --from=build /app/build ./

# Copy Nginx config (for React router support)
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Expose port
EXPOSE 80

# Healthcheck
HEALTHCHECK CMD wget -qO- http://localhost/ || exit 1
