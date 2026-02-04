#!/bin/bash

printf "\nInstalling fish shell\n\n"

sudo apt-add-repository ppa:fish-shell/release-2
sudo apt-get update
sudo apt-get install fish -y
chsh -s $(which fish)


printf "\nDownloading and setting up dotfiles\n\n"
sudo apt-get install -y git-core
git clone git@github.com:talk2bryan/dotfiles.git
ln -s dotfiles/config/fish $HOME/.config/fish
touch ~/.config/fish/includes/private_aliases.fish

mv dotfiles/vimrc ~/.vimrc 
sudo rm -r dotfiles

