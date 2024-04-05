FROM node:latest AS development

# Declaring env
ENV NODE_ENV development

# Setting up the work directory
WORKDIR /clientreact

# Installing dependencies
COPY ./package*.json /clientreact

RUN npm install

# Copying all the files in our project
COPY . .

# Starting our application
CMD ["npm","start"]