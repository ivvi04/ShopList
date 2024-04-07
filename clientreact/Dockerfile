FROM node:latest AS development
ENV NODE_ENV development
WORKDIR /clientreact
COPY ./package*.json /clientreact
RUN npm install
COPY . .
CMD ["npm","start"]