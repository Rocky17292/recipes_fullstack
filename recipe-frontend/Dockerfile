FROM node:18-bullseye

WORKDIR /app

COPY . .

# Copy Docker-specific environment
COPY .env.docker .env

RUN npm install
RUN npm run build

EXPOSE 5173

CMD ["npm", "run", "preview", "--", "--host"]
